#!/usr/bin/env bash

docker run -d -p 15672:15672 -p 5672:5672 -h rabbitmq --name rabbitmq --network=punchat -v /var/log/punchat/rabbitmq.log:/var/log/rabbitmq/rabbitmq.log --network-alias=rabbitmq rabbitmq:management