package grails

import groovyx.net.http.Method
import net.sf.json.JSONArray
import net.sf.json.JSONObject

import javax.ws.rs.POST

import static groovyx.net.http.ContentType.JSON
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST

@Transactional(readOnly = true)
class RecipeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def http = new HTTPBuilder('http://localhost:8081/api/1.0/')
        http.auth.basic "boozelogger","unification"
        def recipeList = new ArrayList<Recipe>()

        http.get( path: 'recipe' ) { response,json ->
            for (recipeObject in json) {
                def recipe = new Recipe()
                recipe.id = recipeObject.get("id")
                recipe.name = recipeObject.get("name")
                recipe.type = recipeObject.get("type")
                recipe.createdAt = new Date(recipeObject.get("createdAt"))
                recipeList.add(recipe)
            }
        }

        respond recipeList, model:[recipeInstanceCount: recipeList.size()]
    }

    def show(Recipe recipeInstance) {
        respond recipeInstance
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

        def http = new HTTPBuilder('http://localhost:8081/api/1.0/recipe')
        http.auth.basic "boozelogger","unification"
        http.request(groovyx.net.http.Method.POST, JSON) { req ->
            body = [ 'id' : recipeInstance.id,
                    'name': recipeInstance.name,
                    'type': recipeInstance.type,
                    'createdAt' : recipeInstance.createdAt]

            response.success = { resp, json ->
                println "POST response: ${resp.statusLine}"
                println json
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
