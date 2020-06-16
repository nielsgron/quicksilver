# Quicksilver
Simple Java Application Server

## Continuous demo

Add to ~/.simpledemo/config/config-application.properties the following

```
quicksilver.autoredeploy=true
```

The execute Gradle in continous mode:

gradle :quicksilver-webserver:run --continuous --watch-fs
