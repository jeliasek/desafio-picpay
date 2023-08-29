# desafio-picpay
Desafio Back-end PicPay - Jr

### Payload - Create User
POST - /users
```json
{
    "firstName": "Zé",
    "lastName": "Colmeia",
    "document": "08518171067",
    "email": "zecolmeia@gmail.com",
    "type": "NORMAL",
    "balance": 50,
    "password": "123"
}
```

```json
{
    "firstName": "Coelho",
    "lastName": "Ronaldo",
    "document": "85511520098",
    "email": "coelhoRonaldo@gmail.com",
    "type": "NORMAL",
    "balance": 0,
    "password": "123"
}
```

```json
{
    "firstName": "Batata",
    "lastName": "Restaurantes",
    "document": "22416044000130",
    "email": "batatarest@gmail.com",
    "type": "MERCHANT",
    "balance": 0,
    "password": "123"
}
```

### Payload - Create Transaction
POST - /transactions
```json
{
    "sender": 1,
    "receiver": 2,
    "amount": 10
}
```
