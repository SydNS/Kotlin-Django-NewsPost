from django.contrib import admin
from .models import WebModel, Comment


# Register your models here.#first add your app to the list of installled apps
# admin.site.register(WebModel)
@admin.register(WebModel)
class WebModelAdmin(admin.ModelAdmin):
    list_display = ('title', 'subtitle', 'author', 'post', 'creationDate')
    list_filter = ('title', 'post', 'creationDate', 'author')
    search_fields = ('title', 'author')
    ordering = ('creationDate','author')



@admin.register(Comment)
class CommentAdmin(admin.ModelAdmin):
    list_display = ('name', 'email', 'post', 'created', 'active')
    list_filter = ('active', 'created', 'updated')
    search_fields = ('name', 'email', 'body')
