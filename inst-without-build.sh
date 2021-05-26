#!/bin/sh

## make this file runnable
chmod +x ./inst-without-build.sh

## add namegenerator to service ##
echo y | cp ./namegenerator.service /etc/systemd/system/
systemctl daemon-reload
systemctl enable namegenerator
#systemctl start namegenerator
systemctl restart namegenerator

## add config to nginx ##
echo y | cp ./namegenerator.nginx.http.conf /etc/nginx/conf.d/
systemctl restart nginx ## please make sure nginx installed ##
