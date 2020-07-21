from django.contrib.auth import authenticate
from django.contrib.sites import requests
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import viewsets, generics, status
from rest_framework.views import APIView
from django.contrib.auth.models import User

import requests
from .serializers import BlogSerializer, UserSerializer, CommentSerializer, UserListSerializer
from django.shortcuts import render, redirect, get_object_or_404
from django.core import serializers

from ..models import WebModel, Comment


class BlogApiView(viewsets.ModelViewSet):
    queryset = WebModel.objects.order_by('-id')
    serializer_class = BlogSerializer


class CommentApiView(viewsets.ModelViewSet):
    queryset = Comment.objects.order_by('-id')
    serializer_class = CommentSerializer


# ------this is for creating users
class UserCreate(generics.CreateAPIView):
    authentication_classes = ()
    permission_classes = ()
    serializer_class = UserSerializer


# -----this is for logging in authenticating users
class LoginView(APIView):
    permission_classes = ()

    def post(self, request, ):
        username = request.data.get("username")
        password = request.data.get("password")
        user = authenticate(username=username, password=password)
        if user:
            return Response({"Message": "logged in"})
            # return Response({"token": user.auth_token.key})
        else:
            return Response({"Message": "Wrong Credentials"}, status=status.HTTP_400_BAD_REQUEST)


class UsersListApiView(viewsets.ModelViewSet):
    queryset = User.objects.order_by('-id')
    serializer_class = UserListSerializer






# retrieveing the users in the system
@api_view(['GET', ])
def PostAndComment(self, id):
    thepost = WebModel.objects.get(id=id)
    title = thepost.title
    thecomment = thepost.comments.all()
    # alltheobjects=[thepost]
    # serializer = serializers.serialize('json',thecomment)
    serializer = CommentSerializer(thecomment, many=True)
    return Response({title + "'s comments": serializer.data})

# post from a sigle user by name
@api_view(['GET', ])
def From_User(self, author):
    posts = WebModel.objects.all().filter(author=author)
    serializer = BlogSerializer(posts,many=True)
    return Response({"title" : serializer.data})


# making a user
# adding more user to the system
# @api_view(['POST', ])
# def userRegistering(request):
#     serializer = UserSerializerForApi(data=request.data)
#     if serializer.is_valid():
#         serializer.save()
#         usersRegistered = UserReg.objects.all()
#         serializer = UserSerializerForApi(usersRegistered, many=True)
#         return Response({"Registered Users": serializer.data})
#
#     return Response({"Error": serializer.error_messages})
#
#
# retrieveing the users in the system
@api_view(['GET', ])
def NewApi(self):
    # url = ('https://newsapi.org/v2/top-headlines?country=us&apiKey=9554ed918d7b460ba72fd9bad6ecd334')
    url = 'https://newsapi.org/v2/sources?language=en&country=us&apiKey=9554ed918d7b460ba72fd9bad6ecd334'

    response = requests.get(url)
    print()
    # newsapi = response.json()["articles"]
    # for x in newsapi:
    #     newsapidataauthor=x["author"]
    #     newsapidatatitle=x["title"]
    #     newsapidatadescription=x["description"]
    #     newsapidict=[{"author":newsapidataauthor,"title":newsapidatatitle,"desc":newsapidatadescription}]
    #
    # return Response( newsapidict)
    # return Response( newsapi)
    return Response(response)
    #
    # {
    #     "source": {
    #         "id": null,
    #         "name": "HuffPost"
    #     },
    #     "author": "AP",
    #     "title": "Kanye West Criticizes Harriet Tubman At His Political Rally - HuffPost",
    #     "description": "West delivered a lengthy monologue in South Carolina at his first event since declaring himself a presidential candidate.",
    #     "url": "https://www.huffpost.com/entry/kanye-west-criticizes-harriet-tubman-at-his-political-rally_n_5f1566f6c5b619afc403dc67",
    #     "urlToImage": "https://img.huffingtonpost.com/asset/5f15672e1f00003d0e338554.jpeg?cache=zixfxfh4gh&ops=1778_1000",
    #     "publishedAt": "2020-07-20T09:49:00Z",
    #     "content": "COLUMBIA, S.C. (AP) Rapper Kanye West, in his first event since declaring himself a presidential candidate, ranted against historical figure Harriet Tubman on Sunday, saying the Underground Railroad … [+2229 chars]"
    # }

# @api_view(['POST', ])
# def loginAuthenticating(request):
#     serializer = UserAuthenticationForApi(data=request.data)
#     # if serializer.is_valid():
#     email = request.POST['email']
#     usersInfo = UserReg.objects.get(email=email)
#     for x in usersInfo:
#         if email == x['email']:
#             return Response({"Resposnse": 1})
#
#     return Response(serializer.error_messages)
