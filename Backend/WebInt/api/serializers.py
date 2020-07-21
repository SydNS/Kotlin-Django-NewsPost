from django.contrib.auth import authenticate
from rest_framework import serializers, status
from rest_framework.authtoken.models import Token
from rest_framework.views import APIView
from rest_framework.response import Response

from ..models import WebModel, Comment

from django.contrib.auth.models import User


# ...creating a user
class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'email', 'password')
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        user = User(email=validated_data['email'], username=validated_data['username'])
        user.set_password(validated_data['password'])
        user.save()
        Token.objects.create(user=user)
        return user


# ...Listing the users
class UserListSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'email')


# ------this is for Blog activities CRUD
class BlogSerializer(serializers.ModelSerializer):
    class Meta:
        model = WebModel
        fields = ['title', 'subtitle', 'post', 'author', 'creationDate', 'id', 'comments']
        depth = 1
        # fields = '__all__'


class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        
        fields = ['post', 'name', 'email', 'body', 'created']
        # fields = '__all__'

#
# # this is for registering and showing users
# class UserSerializerForApi(serializers.ModelSerializer):
#     class Meta:
#         model = UserReg
#         fields = ('username', 'email', 'pasword')
#         # fields='__all__'
#
#
# class UserAuthenticationForApi(serializers.ModelSerializer):
#     class Meta:
#         model = UserReg
#         fields = ('username', 'email')
#         # fields='__all__'
