#!/usr/bin/env bash

docker run -d --name consul -p 8500:8500 --network punchat --alias=consul -h consul consul