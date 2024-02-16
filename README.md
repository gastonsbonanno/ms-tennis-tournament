# ms-tennis-tournament
Geopagos challenge


## Endpoints
#### [POST] http://localhost:8080/match/execute
* Path variables: matchGender (Opciones -> MALE, FEMALE)

* Body:
    "players": [
        {
            "name": String,
            "skillLevel": Number,
            "strength": Number,
            "speed": Number,
            "reaction": Number,
            "gender": String
        }
    ]

* Example: curl --location 'http://localhost:8080/match/execute?matchGender=MALE' \
--header 'Content-Type: application/json' \
--data '{
"players": [
{
"name": "Pedro",
"skillLevel": "60",
"strength": "30",
"speed": "40",
"reaction": "50",
"gender": "MALE"
},
{
"name": "Roberto",
"skillLevel": "60",
"strength": "30",
"speed": "40",
"reaction": "50",
"gender": "MALE"
},
{
"name": "María",
"skillLevel": "60",
"strength": "30",
"speed": "40",
"reaction": "50",
"gender": "FEMALE"
}
]
}'


#### [GET]http://localhost:8080/match/results
* Path variables: gender (Opciones -> MALE, FEMALE),
                date (Format yyyy-MM-dd),
                winnerName (String)

* Example: curl --location 'http://localhost:8080/match/results?gender=MALE&date=2024-02-16&winnerName=Julian'

## Swagger
* http://localhost:8080/swagger-ui/index.html

## Api Docs
* http://localhost:8080/v3/api-docs
