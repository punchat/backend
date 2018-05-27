#!/usr/bin/env bash

docker run -d -p 9411:9411 --name zipkin --network=punchat -e RABBIT_ADDRESSES=rabbitmq openzipkin/zipkin