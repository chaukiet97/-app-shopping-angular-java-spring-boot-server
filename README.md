# ADMIN TOOL API


Webhook tự động xác nhận thanh toán
=========

Chạy 
------

```bash
./mvnw spring-boot:run
```

Test
--------
```bash
curl --location --request POST 'http://13.125.127.101:3332/admin-tool/payment/bank_transfer_handler' \
--header 'secure-token: w3bkulq38wbqdSYd' \
--header 'Content-Type: application/json' \
--data-raw '{
    "error": 0,
    "data": [
        {
            "when": "2020-12-23",
            "amount": 850000,
            "description": "NAP1",
            "cusum_balance": 15200000,
            "tid": "TF80307914",
            "subAccId": "123456789",
            "order": "2020110200001"
        }
    ]
}'
```

Build
--------

```bash
./mvnw package
```

Release
--------
Dùng lệnh scp để copy file jar lên server
```bash
HOST_STR=root@api.admin.casso.vn
HOST_STR=ubuntu@13.125.127.101
scp target/casso-admin-tool-1.0.0.jar $HOST_STR:~/casso-admintool/casso-admin-tool-lastest.jar

```

Sau đó truy cập vào server và restart lại service

```bash
ssh $HOST_STR sudo systemctl restart casso
```


Devops
--------
Cài đặt java 11 trở lên
```
apt update
apt install default-jdk
```
Nếu default-jdk chưa lên dc 11 , thì phải tìm cách cài. 
Ví dụ như Ubuntu 18.04 (Vultr) thì có thể dùng cách dưới.
```
sudo add-apt-repository -y ppa:openjdk-r/ppa
sudo apt update -qq
sudo apt install openjdk-11-jdk
```

Test phiên bản
```
java -version
```


Copy ma nguon len server

```bash
HOST_STR=root@admin.casso.vn
ssh root@admin.casso.vn mkdir casso-admintool
scp target/casso-admin-tool-1.0.0.jar $HOST_STR:~/casso-admintool/casso-admin-tool-lastest.jar
scp devops/start.sh $HOST_STR:~/casso-admintool/start.sh
scp devops/casso.service $HOST_STR:~/casso-admintool/casso.service
scp src/main/resources/application.yml $HOST_STR:~/casso-admintool/application.yml
```

Cấu hình lại các thông số truy cập DB và Server tại application.yml
```
nano ~/casso-admintool/application.yml
```

Login vào server và tạo cấu hình service tron systemd
```
sudo cp ~/casso-admintool/casso.service /etc/systemd/system/casso.service
```

Khởi chạy service
```
sudo systemctl daemon-reload
sudo systemctl enable casso.service
sudo systemctl start casso
```

Theo dõi 
---------
```
sudo journalctl -f -n 1000 -u casso
```


API Admin dashboard
==========
bao gồm các api dc dùng cho website admin.casso.vn
