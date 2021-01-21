sudo mvn clean install
sudo docker build -t url-key-generator .
cd ..
sudo docker-compose up