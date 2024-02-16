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


## Database
### Name: tennis_tournament
* Tables: tournament_results
* Fields: 
- [id] [int] IDENTITY(1,1) NOT NULL,
- [execution_date] [date] NOT NULL,
- [gender] [varchar](255) NOT NULL,
- [winner_name] [varchar](255) NOT NULL,
- [matchs_played] [int] NOT NULL, 

### Script para la creación de la base completa:

USE [master]
GO
/****** Object:  Database [tennis_tournament]    Script Date: 2/16/2024 7:52:27 PM ******/
CREATE DATABASE [tennis_tournament]
CONTAINMENT = NONE
ON  PRIMARY
( NAME = N'tennis_tournament', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER01\MSSQL\DATA\tennis_tournament.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
LOG ON
( NAME = N'tennis_tournament_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER01\MSSQL\DATA\tennis_tournament_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [tennis_tournament] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [tennis_tournament].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [tennis_tournament] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [tennis_tournament] SET ANSI_NULLS OFF
GO
ALTER DATABASE [tennis_tournament] SET ANSI_PADDING OFF
GO
ALTER DATABASE [tennis_tournament] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [tennis_tournament] SET ARITHABORT OFF
GO
ALTER DATABASE [tennis_tournament] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [tennis_tournament] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [tennis_tournament] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [tennis_tournament] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [tennis_tournament] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [tennis_tournament] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [tennis_tournament] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [tennis_tournament] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [tennis_tournament] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [tennis_tournament] SET  DISABLE_BROKER
GO
ALTER DATABASE [tennis_tournament] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [tennis_tournament] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [tennis_tournament] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [tennis_tournament] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [tennis_tournament] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [tennis_tournament] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [tennis_tournament] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [tennis_tournament] SET RECOVERY FULL
GO
ALTER DATABASE [tennis_tournament] SET  MULTI_USER
GO
ALTER DATABASE [tennis_tournament] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [tennis_tournament] SET DB_CHAINING OFF
GO
ALTER DATABASE [tennis_tournament] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [tennis_tournament] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [tennis_tournament] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [tennis_tournament] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'tennis_tournament', N'ON'
GO
ALTER DATABASE [tennis_tournament] SET QUERY_STORE = ON
GO
ALTER DATABASE [tennis_tournament] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [tennis_tournament]
GO
/****** Object:  Table [dbo].[tournament_results]    Script Date: 2/16/2024 7:52:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tournament_results](
[id] [int] IDENTITY(1,1) NOT NULL,
[execution_date] [date] NOT NULL,
[gender] [varchar](255) NOT NULL,
[winner_name] [varchar](255) NOT NULL,
[matchs_played] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [tennis_tournament] SET  READ_WRITE
GO
