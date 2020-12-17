#!/usr/bin/env bash

docker image build -t taghuofongue/cave-enki-backend .

docker push taghuofongue/cave-enki-backend

kubectl create ns cave-enki