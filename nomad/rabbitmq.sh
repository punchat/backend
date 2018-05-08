#!/usr/bin/env bash

docker run -d -h rabbitmq --name rabbitmq --network=punchat --network-alias=rabbitmq rabbitmq:management