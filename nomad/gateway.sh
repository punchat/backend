#!/usr/bin/env bash

docker run -d --name gateway -p 8080:8080 --network punchat --network-alias=gateway -h gateway --env-file=gateway.env punchat/gateway