package com.github.kodomo.dsmpayments.infra.config.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.github.kodomo.dsmpayments.DsmPaymentsApplication;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketConnect;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketController;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketDisConnect;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketMapping;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class SocketConfig {

    private static final String SOCKET_CONTROLLER_PATH = "com.github.kodomo.dsmpayments";

    @Value("${server.socket.port}")
    private Integer port;

    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(port);
        server = new SocketIOServer(config);
        server.start();

        return server;
    }

    @PostConstruct
    public void setup() {
        getModuleConfiguration().forEach(controller -> {
            Object controllerInstance;
            try {
                Constructor<?> constructor = controller.getConstructor();
                controllerInstance = constructor.newInstance();
            } catch (Exception ignored) {
                return;
            }
            for (Method method : controller.getDeclaredMethods()) {
                if (method.getDeclaredAnnotation(SocketConnect.class) != null ||
                        method.getDeclaredAnnotation(SocketDisConnect.class) != null) {
                    server.addConnectListener(client -> {
                        try {
                            List<Object> args = new ArrayList<>();
                            for (Class<?> parameter : method.getParameterTypes()) {
                                if (parameter.equals(SocketIOServer.class)) args.add(server);
                                else if (parameter.equals(SocketIOClient.class)) args.add(client);
                            }
                            method.invoke(controllerInstance, args.toArray());
                        } catch (IllegalAccessException | InvocationTargetException ignored) {}
                    });
                } else if (method.getDeclaredAnnotation(SocketMapping.class) != null) {
                    SocketMapping mappingData = method.getDeclaredAnnotation(SocketMapping.class);
                    String endpoint = mappingData.value();
                    Class<?> requestDTO = mappingData.requestDTO();
                    server.addEventListener(endpoint, requestDTO, (client, data, ackSender) -> {
                        List<Object> args = new ArrayList<>();
                        for (Class<?> parameter : method.getParameterTypes()) {
                            if (parameter.equals(SocketIOServer.class)) args.add(server);
                            else if (parameter.equals(SocketIOClient.class)) args.add(client);
                            else if (parameter.equals(requestDTO)) args.add(data);
                        }
                        method.invoke(controllerInstance, args.toArray());
                    });
                }
            }
        });
    }

    @PreDestroy
    public void socketStop() {
        server.stop();
    }

    @SneakyThrows
    private static List<Class<?>> getModuleConfiguration() {
        ClassLoader classLoader = DsmPaymentsApplication.class.getClassLoader();
        assert classLoader != null;

        String path = SOCKET_CONTROLLER_PATH.replace('.', '/');
        URL modulePathUrl = classLoader.getResource(path);
        assert modulePathUrl != null;
        File pathDirectory = new File(modulePathUrl.getFile());
        if (pathDirectory.isDirectory()) {
            return findClassesFromDirectory(pathDirectory, SOCKET_CONTROLLER_PATH);
        } else {
            String pathString = pathDirectory.getPath();
            pathString = pathString.substring(pathString.indexOf(":") + 1, pathString.indexOf("!"));
            return findClassesFromJar(pathString);
        }
    }

    private static List<Class<?>> findClassesFromJar(String filePath) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName().replace("/", ".");
                if (!fileName.endsWith(".class")) continue;
                if (!fileName.contains(SOCKET_CONTROLLER_PATH)) continue;
                fileName = fileName.substring(fileName.indexOf(SOCKET_CONTROLLER_PATH), fileName.lastIndexOf("."));

                Class<?> clazz = Class.forName(fileName);
                if (clazz.getDeclaredAnnotation(SocketController.class) != null) classes.add(clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }


    private static List<Class<?>> findClassesFromDirectory(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClassesFromDirectory(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                Class<?> clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                if (clazz.getDeclaredAnnotation(SocketController.class) != null) classes.add(clazz);
            }
        }
        return classes;
    }

}
