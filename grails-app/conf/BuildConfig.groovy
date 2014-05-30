grails.project.repos.default = 'bintray-stokito-maven-grails-url-shortener'
grails.project.repos.'bintray-stokito-maven-grails-url-shortener'.url = 'https://api.bintray.com/maven/stokito/maven/grails-url-shortener'
grails.project.repos.'bintray-stokito-maven-grails-url-shortener'.type = 'maven'
grails.project.repos.'bintray-stokito-maven-grails-url-shortener'.portal = 'stokitoBintray'

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.5'
    }
	plugins {
        provided ':webxml:1.4.1'
		runtime ':rest:0.8'

        build(':release:2.2.1', ':rest-client-builder:1.0.3') {
            export = false
        }
	}
}
