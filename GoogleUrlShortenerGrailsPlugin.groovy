import org.grails.plugins.googleurlshortener.ShortenerService;

class GoogleUrlShortenerGrailsPlugin {
	// the plugin version
	def version = "0.2"
	// the version or versions of Grails the plugin is designed for
	def grailsVersion = "2.3.5 > *"
	// resources that are excluded from plugin packaging
	def pluginExcludes = [
		"grails-app/views/error.gsp"
	]
	def author = "Sebastien Blanc"
	def authorEmail = "sblanc@e-id.nl"
	def title = "Google URL Shortener Service"
	def description = '''\\
This plugins provides a abstraction layer around the google URL shortener service, you can shorten, expand and retrieve analytics from URL following the grails conventions.
'''
	// URL to the plugin's documentation
	def documentation = "http://grails.org/plugin/google-url-shortener"
    def license = 'APACHE'
    def issueManagement = [system: "GitHub", url: "https://github.com/stokito/grails-url-shortener/issues"]
    def scm = [url: "https://github.com/stokito/grails-url-shortener"]

    def doWithDynamicMethods = { applicationContext ->
		ShortenerService service = applicationContext.getBean('shortenerService')
		String.metaClass.shorten = {->
			def urlResource = service.shorten(delegate)
			return urlResource.shortUrl
		}
		String.metaClass.expand = {->
			def urlResource = service.expand(delegate)
			return urlResource.longUrl
		}
	}
}
