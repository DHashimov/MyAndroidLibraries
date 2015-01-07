#!/bin/bash
# Call this script with at least 3 parameters, for example
# sh scriptname 1 

$ path = $1

cd
cd /home/deniz/TOMCAT/apache-tomcat-7.0.57/bin/
./shutdown.sh

cd
cd /home/deniz/TOMCAT/apache-tomcat-7.0.57/webapps/

echo ##############################################################################################################################
echo                                                ----- REMOVE OLD BUILD ------
echo rm -rf ${1}

echo ##############################################################################################################################
sudo rm -rf ${1}

echo ##############################################################################################################################
echo                                                ----- COPY NEW BUILD ------
echo cp /home/deniz/Server/Builds/${1} /home/deniz/TOMCAT/apache-tomcat-7.0.57/webapps/${1}

echo ##############################################################################################################################
sudo cp /home/deniz/Server/Builds/${1} /home/deniz/TOMCAT/apache-tomcat-7.0.57/webapps/${1}


cd
cd /home/deniz/TOMCAT/apache-tomcat-7.0.57/bin/

./startup.sh




