<%@ page import="grails.Recipe" %>



<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'createdAt', 'error')} required">
	<label for="createdAt">
		<g:message code="recipe.createdAt.label" default="Created At" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createdAt" precision="day"  value="${recipeInstance?.createdAt}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="recipe.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${recipeInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="recipe.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="type" from="${boozelogger.entity.RecipeType?.values()}" keys="${boozelogger.entity.RecipeType.values()*.name()}" required="" value="${recipeInstance?.type?.name()}" />

</div>

