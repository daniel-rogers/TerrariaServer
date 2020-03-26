FROM ubuntu:18.04
RUN cd /opt && sudo curl -O http://terraria.org/server/terraria-server-1344.zip
RUN sudo apt install unzip
RUN sudo unzip terraria-server-1344.zip
RUN sudo mv /opt/Dedicated\ Server/Linux /opt/terraria
RUN sudo rm -rf Dedicated\ Server/
RUN sudo chown -R root:root /opt/terraria
RUN sudo chmod +x /opt/terraria/TerrariaServer.bin.x86_64
RUN sudo useradd -r -m -d /srv/terraria terraria
RUN sudo mv /opt/terraria/serverconfig.txt /opt/terraria/serverconfig.txt.bak
ADD ./files/serverconfig.txt /opt/terraria
RUN sudo apt install screen
ADD ./files/terraria.service /etc/systemd/system
ADD ./files/terrariad /usr/local/bin
RUN sudo chmod +x /usr/local/bin/terrariad
ADD sudo systemctl enable terraria
ADD sudo systemctl start terraria