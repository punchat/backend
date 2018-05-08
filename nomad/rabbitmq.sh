#!/usr/bin/env bash

docker run -h rabbitmq --name rabbitmq --network=punchat --alias=rabbitmq rabbitmq:management