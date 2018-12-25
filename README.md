Validate CSV and XML records for validity.
Execute as 
curl -X POST \
  http://localhost:8080/verifyStatment \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Postman-Token: dfde95eb-6d7d-4674-b529-dd898fb901ce' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'recordStatement=@D:\a.csv'
  
  The end point accepts both CSV and XML files.
