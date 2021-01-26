sudo mvn clean install
sudo docker build -t url-key-manager .
cd ..
sudo docker-compose up