from django.db import models
from django.utils import timezone


# Create your models here.
class WebModel(models.Model):
    title = models.CharField(max_length=20)
    subtitle = models.TextField(max_length=40)
    post = models.TextField(max_length=100000)
    author = models.TextField(max_length=20)
    creationDate = models.DateTimeField(default=timezone.now)
    #
    # def __str__(self):
    #     return 'Title : {},SubTitle : {},Posts : {},Author : {},Date_added : {}'.format(self.title, self.subtitle,
    #                                                                                     self.post, self.author,
    #                                                                                     self.creationDate)

    def __str__(self):
        return self.title


class Comment(models.Model):
    post = models.ForeignKey(WebModel,
                             on_delete=models.CASCADE,
                             related_name='comments')
    name = models.CharField(max_length=80)
    email = models.EmailField()
    body = models.TextField()
    created = models.DateTimeField(auto_now_add=True)
    updated = models.DateTimeField(auto_now=True)
    active = models.BooleanField(default=True)

    class Meta:
        ordering = ('created',)

    def __str__(self):
        return self.name
        # return f'Comment by {self.name} on {self.post}'


