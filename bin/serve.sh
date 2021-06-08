#!/bin/sh

## make this file runnable
chmod +x ./*.sh

cd ../

## build the jar ##
gradle bootJar --no-daemon
mkdir -p /root/jars
cp ./build/libs/namegenerator-1.0.jar /root/jars/namegenerator.jar

cd ./bin/config

## add namegenerator to service ##
echo y | cp ./namegenerator.service /etc/systemd/system/
systemctl daemon-reload
systemctl enable namegenerator
#systemctl start namegenerator
systemctl restart namegenerator

## add config to nginx ##
echo y | cp ./namegenerator.nginx.http.conf /etc/nginx/conf.d/
systemctl restart nginx ## please make sure nginx installed ##
