## Local Docker Testing

Build the Docker image locally:

```bash
docker build --target runtime -t product-managment:local .
```

Run the Docker container:

```bash
docker run --rm -p 8080:8080 product-managment:local
```

The application will then be available on:

```text
http://localhost:8080
```

The `--rm` option automatically removes the container when it is stopped.
