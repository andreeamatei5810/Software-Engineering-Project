# Smart farming device

### About the project

The developed application offers an intelligent solution in the agriculture field for a prosperous harvest both by analyzing 
the soil and the weather providing relevant data, as well as by programming the irrigation system.

### Technologies used

* Spring Boot
* SQL
* Docker
* RESTLER

### Prerequisites

* Docker for database creation
    * [This link](https://docs.docker.com/get-docker/) can be used for downloading.
* Mosquitto for MQTT communication
    * [This link](https://mosquitto.org/download/) can be used for downloading MQTT broker.
    * Port 1883 must be opened.
    * [Tutorial](https://bytesofgigabytes.com/mqtt/installing-mqtt-broker-on-windows/)
* Python 3 and .NET 5.0 for automated testing
    * [This link](https://www.python.org/downloads/) can be used for downloading python.
    * [This link](https://dotnet.microsoft.com/en-us/download/dotnet/5.0) can be used for downloading .NET.

### Installation
Clone the repo:
```
git clone https://github.com/andreeamatei5810/Software-Engineering-Project.git
```

### Build
To start the database run:
```
docker compose-up
```

### Usage

The functionalities of the application can be seen in the 
[analysis documentation](https://github.com/andreeamatei5810/Software-Engineering-Project/blob/master/Document%20de%20analiz%C4%83%20a%20cerin%C8%9Belor%20clientului%20(update).docx)
and the specifications of [OpenAPI](https://github.com/andreeamatei5810/Software-Engineering-Project/blob/master/open_api.yml)
and [AsyncAPI](https://github.com/andreeamatei5810/Software-Engineering-Project/blob/master/async_api.yml).

### Automated testing
Automatic testing was performed using RESTLER. After python 3 and .NET 5 have been installed, the following commands must be run to use automated testing:
```
mkdir restler_bin
git clone https://github.com/microsoft/restler-fuzzer.git
cd <Path to restler-fuzzer dir>
python ./build-restler.py --dest_dir <Full Path for restler_bin dir>
python restler-quick-start.py --api_spec_path <Full path to open_api.yml>  --restler_drop_dir <Full path to restler_bin dir>
```