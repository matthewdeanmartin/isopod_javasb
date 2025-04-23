include .env
export

.PHONY: all fmt check bugs vuln test fullcheck

all: fmt test

strict: fmt check bugs vuln test

fmt:
	echo "mvn formatter:format"

check:
	echo mvn checkstyle:check

bugs:
	mvn spotbugs:check

vuln:
	mvn org.owasp:dependency-check-maven:check

test:
	mvn test

fullcheck:
	mvn formatter:format \
	    checkstyle:check \
	    spotbugs:check \
	    org.owasp:dependency-check-maven:check \
	    test

clean:
	mvn clean

package:
	mvn package

deploy:
	sls deploy --stage dev
