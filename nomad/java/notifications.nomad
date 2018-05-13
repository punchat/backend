job "notifications-service" {
  datacenters = ["dc1"]
  type = "service"
  group "notifications" {
    count = 2
    task "notifications" {
      driver = "java"
      config {
        jar_path = "local/notifications.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/notifications.jar"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        accessTokenUri = "http://167.99.139.178:8080/uaa/oauth/token"
      }
      resources {
        cpu = 300
        memory = 450
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