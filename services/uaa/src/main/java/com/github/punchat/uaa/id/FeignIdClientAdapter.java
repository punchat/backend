package com.github.punchat.uaa.id;

/**
 * @author Alex Ivchenko
 */
public class FeignIdClientAdapter implements IdService {
    private final IdClient idClient;

    public FeignIdClientAdapter(IdClient idClient) {
        this.idClient = idClient;
    }

    @Override
    public Long next() {
        return idClient.next();
    }
}
