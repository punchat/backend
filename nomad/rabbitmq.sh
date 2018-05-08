#!/usr/bin/env bash

docker run -h rabbitmq --name rabbitmq --network=punchat --network-alias=rabbitmq rabbitmq:management