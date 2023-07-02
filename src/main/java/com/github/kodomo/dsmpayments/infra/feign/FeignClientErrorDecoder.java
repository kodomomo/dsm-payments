package com.github.kodomo.dsmpayments.infra.feign;

import com.github.kodomo.dsmpayments.infra.feign.exception.FeignException;
import com.github.kodomo.dsmpayments.infra.feign.exception.FeignExpiredTokenException;
import com.github.kodomo.dsmpayments.infra.feign.exception.FeignForbiddenException;
import com.github.kodomo.dsmpayments.infra.feign.exception.FeignUnAuthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) throws feign.FeignException {

        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw new FeignUnAuthorizedException();
                case 403:
                    throw new FeignForbiddenException();
                case 419:
                    throw new FeignExpiredTokenException();
                default:
                    throw new FeignException(
                            response.status(),
                            response.reason()
                    );
            }
        }

        return feign.FeignException.errorStatus(methodKey, response);
    }
}
