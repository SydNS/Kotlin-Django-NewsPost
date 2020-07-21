from django import forms
#
from .models import Comment


class WebIntForm(forms.Form):
    title = forms.CharField(max_length=40,
                            widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Title'}))
    subtitle = forms.CharField(widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'SubTitle'}))
    post = forms.CharField(max_length=20000,
                           widget=forms.Textarea(attrs={'class': 'form-control', 'rows': '4', 'placeholder': 'Post'}))
    author = forms.CharField(max_length=80,
                             widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Author'}))


class ShareForm(forms.Form):
    name = forms.CharField(max_length=25,
                           widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Title'}))
    email = forms.EmailField(max_length=25,
                             widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Email'}))
    to = forms.EmailField(max_length=25,
                          widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Email'}))
    comments = forms.CharField(widget=forms.Textarea(
        attrs={'class': 'form-control', 'rows': '3', 'placeholder': 'Comments'}))


class CommentForm(forms.ModelForm):
    class Meta:
        model = Comment
        fields = ('name', 'email', 'body')
