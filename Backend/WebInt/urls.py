from django.urls import path, include
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('posts', views.theposted, name='posts'),
    path('createpost', views.makePosts, name='makepost'),
    path('posts/<int:id>/', views.singlePost, name='singlepost'),
    path('post/<int:id>/share/', views.sharePosts, name='sharepost')
]
