package grails

import groovyx.net.http.Method
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.apache.http.HttpRequest
import org.apache.http.HttpRequestInterceptor
import org.apache.http.protocol.HttpContext

import javax.ws.rs.POST

import static groovyx.net.http.ContentType.JSON
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovyx.net.http.HTTPBuilder

@Transactional(readOnly = true)
class RecipeController {

    static def api = new HTTPBuilder('http://localhost:8081/api/1.0/recipe/')
    static {
        api.client.addRequestInterceptor(new HttpRequestInterceptor() {
            void process(HttpRequest httpRequest, HttpContext httpContext) {
                httpRequest.addHeader('Authorization', 'Basic ' + 'administrator:unification'.bytes.encodeBase64().toString())
            }
        })
        api.handler.failure = { resp ->
            println "Unexpected failure: ${resp.statusLine}"
        }
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def recipeList = new ArrayList<Recipe>()

        api.request(groovyx.net.http.Method.GET, JSON) {

            response.success = { response,json ->
                for (recipeObject in json) {
                    def recipe = new Recipe()
                    recipe.id = recipeObject.get("id")
                    recipe.name = recipeObject.get("name")
                    recipe.type = recipeObject.get("type")
                    recipe.createdAt = new Date(recipeObject.get("createdAt"))
                    recipeList.add(recipe)
                }
            }
        }

        respond recipeList, model:[recipeInstanceCount: recipeList.size()]
    }

    def show(Integer id) {
        api.request(path: '/' + id, groovyx.net.http.Method.GET, JSON) {

            response.success = { response,json ->
                def recipeInstance = new Recipe()
                recipeInstance.id = recipeObject.get("id")
                recipeInstance.name = recipeObject.get("name")
                recipeInstance.type = recipeObject.get("type")
                recipeInstance.createdAt = new Date(recipeObject.get("createdAt"))

                respond recipeInstance
            }


        }
    }

    def create() {
        respond new Recipe(params)
    }

    @Transactional
    def save(Recipe recipeInstance) {
        if (recipeInstance == null) {
            notFound()
            return
        }

        if (recipeInstance.hasErrors()) {
            respond recipeInstance.errors, view:'create'
            return
        }

        api.request(groovyx.net.http.Method.POST, JSON) { req ->
            body = [ 'id' : recipeInstance.id,
                    'name': recipeInstance.name,
                    'type': recipeInstance.type,
                    'createdAt' : recipeInstance.createdAt.getTime()]

            response.success = { resp, json ->
                println "POST response: ${resp.statusLine}"
                println json
                recipeInstance.id = json['id'];
                recipeInstance.name = json['name']
                recipeInstance.type = json['type']
                recipeInstance.createdAt = new Date(json['createdAt'])
            }
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recipe.label', default: 'Recipe'), recipeInstance.id])
                redirect recipeInstance
            }
            '*' { respond recipeInstance, [status: CREATED] }
        }
    }

    def edit(Recipe recipeInstance) {
        respond recipeInstance
    }

    @Transactional
    def update(Recipe recipeInstance) {
        if (recipeInstance == null) {
            notFound()
            return
        }

        if (recipeInstance.hasErrors()) {
            respond recipeInstance.errors, view:'edit'
            return
        }

        recipeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recipe.label', default: 'Recipe'), recipeInstance.id])
                redirect recipeInstance
            }
            '*'{ respond recipeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Recipe recipeInstance) {

        if (recipeInstance == null) {
            notFound()
            return
        }

        recipeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recipe.label', default: 'Recipe'), recipeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
