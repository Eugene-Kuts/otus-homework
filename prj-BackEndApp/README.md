BackEndApp - приложение эмулирует работу BackEnd Системы.
Принимает на вход три параметра (см. пример запроса), возвращает их произведение (см. пример ответа).

Пример запроса:
{
"paramA": 1,
"paramB": 2,
"paramC": 3
}

Пример ответа:
{
    "paramD": 6
}

Docker:
docker build -t backendapp . && docker run -p 8081:8081 --name BackEndApp -d backendapp

DockerHub link:
https://hub.docker.com/repository/docker/gouliver/otus_project

Pull from DockerHub:
docker pull gouliver/otus_project:BackEndApp