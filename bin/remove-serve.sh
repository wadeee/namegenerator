#!/bin/sh

## make shell file runnable
chmod +x ./*.sh

## remove service
systemctl stop namegenerator
systemctl disable namegenerator
rm /etc/systemd/system/namegenerator.service
systemctl daemon-reload

## remove nginx config
rm /etc/nginx/conf.d/namegenerator.nginx.http.conf
systemctl restart nginx

## remove jar
rm /root/jars/namegenerator.jar
