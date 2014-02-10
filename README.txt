# required software
maven - 3.1.1
install:
sudo apt-get install maven

nodejs
npm - 1.3.21
sudo apt-get install python-software-properties python g++ make
sudo add-apt-repository ppa:chris-lea/node.js
sudo apt-get update
sudo apt-get install nodejs

http-server
sudo npm install http-server

stage 1 & 2
#run scripts
cd startup
sh startup-cloudscale.sh // To start cloudscaling
sh startup-google.sh	// To start google app engine
sh startup-app.sh		// To start the Gui for the analysis

troubleshooting:
app am besten mit npm http-server (node.js)
http.//nodejs.org
https://npmjs.org/package/http-server
http://stackoverflow.com/questions/12905426/faster-alternative-to-pythons-simplehttpserver
