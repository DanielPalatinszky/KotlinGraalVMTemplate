To run in a Docker container:
  1. In CMD/PowerShell: docker run -it -p 8000:8000 -v <directory that contains the template bash script>\:/home/template --name graal oracle/graalvm-ce:1.0.0-rc16 bash
  2. Navigate to the /home/template directory and run ./template <source file>

Always use the shadowJar task to build the project!
