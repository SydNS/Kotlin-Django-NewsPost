from . import views
from django.urls import path, include
from rest_framework import routers

from .views import UserCreate, LoginView

router = routers.DefaultRouter()
router.register('allposts', views.BlogApiView)
router.register('allcomments', views.CommentApiView)
router.register('allusers', views.UsersListApiView)

app_name = 'WebInt'

urlpatterns = [
    path('', include(router.urls)),
    path("both/<int:id>", views.PostAndComment, name="p_c"),
    path("login/", LoginView.as_view(), name="login"),
    path("users/", UserCreate.as_view(), name="user_create"),
    path('newsapi/', views.NewApi, name='newspi1'),
    path('allusers/<str:author>', views.From_User, name='fromauthor')

]

# path('userregistration/', views.userRegistering, name='registeringusers'),
# path('userrauthenticating/', views.loginAuthenticating, name='authenticatingusers'),
# path('registeredusers/', views.getUserinfo, name='getUserinfo'),
