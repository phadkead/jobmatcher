# Job Matcher


## Technology stack
1. Spring Boot
2. Spring Data
3. Mongo DB (bulk insertion, geo geospatial data analysis)
4. Mockito, Junit
5. Spring REST webservices

## How it works
Spring scheduler runs on startup and then every 10 mins to fetch latest data from given third party APIs:
/jobs
/workers

It removes and then bulk inserts fetched data to 2 MongoDB collections: workers, jobs

Our Job matching algorithm always runs on these MongoDB collections.
Job matching preference is based on these critera:
1. Within range of Geo location selected by worker
2. License matching critera specified by job specifier
3. Worker should have all the certificates specified by job specifier
4. Then it returns best three (offcourse, offering highest bill rate!) jobs applicable to worker


## Setup instructions:
- You need to have MongoDB running on localhost:27017
- Create collections and create these indexes
- Import this into your IDE, start up JobmatcherApplication.java 
- REST service to find best jobs for a worker:
/jobMatchingApplication/jobMatches?workerId=0
