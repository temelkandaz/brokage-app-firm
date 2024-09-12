# Brokage Firm App

## Deployment

### Steps

```
1. mvn compile

2. mvn clean package

3. docker build -t brokage-firm-app .

4. docker run -p 8080:8080 brokage-firm-app 
```
