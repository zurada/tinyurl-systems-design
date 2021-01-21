sudo mvn clean install
sudo mvn spring-boot:run
sudo docker build -t url-key-generator .
cd ..
sudo docker-compose up