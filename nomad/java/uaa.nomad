job "uaa-service" {
  datacenters = ["dc1"]
  type = "service"
  group "uaa" {
    count = 2
    task "uaa-api" {
      driver = "java"
      config {
        jar_path = "local/uaa.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/uaa.jar"
      }
      env {
        port = "${NOMAD_HOST_PORT_http}"
        clientId = "uaa"
        clientSecret = "secret"
        accessTokenUri = "http://167.99.139.178:8080/uaa/oauth/token"
      }
      resources {
        cpu = 300
        memory = 500
        network {
          port "http" {}
        }
      }
    }
    restart {
      attempts = 1
    }
  }
}