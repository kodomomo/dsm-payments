package com.github.kodomo.dsmpayments.infra.feign;

import com.github.kodomo.dsmpayments.infra.feign.exception.FeignBadRequestException;
import com.github.kodomo.dsmpayments.infra.feign.exception.FeignExpiredTokenException;
import com.github.kodomo.dsmpayments.infra.feign.exception.FeignForbiddenException;
import com.github.kodomo.dsmpayments.infra.feign.exception.FeignUnAuthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) throws FeignException {

        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw new FeignUnAuthorizedException();
                case 403:
                    throw new FeignForbiddenException();
                case 419:
                    throw new FeignExpiredTokenException();
                default:
                    throw new FeignBadRequestException();
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
