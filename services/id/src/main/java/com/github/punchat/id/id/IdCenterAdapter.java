package com.github.punchat.id.id;

import com.sohu.idcenter.IdWorker;

/**
 * @author Alex Ivchenko
 */
public class IdCenterAdapter implements IdService {
    private final IdWorker worker;

    public IdCenterAdapter(long workerId, long datacenterId, long sequence) {
        worker = new IdWorker(workerId, datacenterId, sequence);
    }

    @Override
    public long next() {
        return worker.getId();
    }
}
