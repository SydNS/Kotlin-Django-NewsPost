from django.core.mail import send_mail
from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse

from .forms import CommentForm
from . import forms
from .models import WebModel
from .forms import WebIntForm, ShareForm
from django.core import serializers
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger

FOE = "IT, Data,CyberSect,Android Dev, FLask & Django,A not certified Google Cloud engineer"
creator = "Syd|Ney"


# Create your testing view here. 1=====================================
def theposted(request):
    data = serializers.serialize("json", WebModel.objects.all())
    return HttpResponse(data)

# Create your views here. 2 mainHomepage=====================================
def index(request):
    allofem = WebModel.objects.order_by('-id')

    paginator = Paginator(allofem, 4)  # 4 posts in each page
    page = request.GET.get('page')
    try:
        posts = paginator.get_page(page)
    except PageNotAnInteger:
        # If page is not an integer deliver the first page
        posts = paginator.get_page(1)
    except EmptyPage:
        # If page is out of range deliver last page of results
        posts = paginator.get_page(paginator.num_pages)
    context = {'allposts': posts, 'creator': creator, 'foe': FOE, 'page': posts}
    return render(request, 'index.html', context)
    #     return HttpResponse(allofem )

# Create your views here. 3 CreatingPost Page=====================================
def makePosts(request):
    if request.method == 'POST':
        fm = WebIntForm(request.POST)
        if fm.is_valid():
            # tspa
            newPost = WebModel(title=request.POST['title'], subtitle=request.POST['subtitle'],
                               post=request.POST['post'], author=request.POST['author'], )
            newPost.save()
            return redirect('index')
    else:
        fm = forms.WebIntForm()

        return render(request, 'contact.html', {'fm': fm, 'creator': creator, 'foe': FOE})

# Create your views here. 4 Post in Detail Page=====================================
def singlePost(request, id):
    fm = forms.ShareForm()
    onePost = WebModel.objects.get(id=id)

    # List of active comments for this post
    comments = onePost.comments.filter(active=True)
    new_comment = None
    if request.method == 'POST':
        # A comment was posted
        comment_form = CommentForm(data=request.POST)
        if comment_form.is_valid():
            # Create Comment object but don't save to database yet
            new_comment = comment_form.save(commit=False)
            # Assign the current post to the comment
            new_comment.post = onePost
            # Save the comment to the database
            new_comment.save()

    else:
        comment_form = CommentForm()
    # return render(request,
    #               'blog/post/detail.html',
    #               {'post': post,
    #                'comments': comments,
    #                'new_comment': new_comment,
    #                'comment_form': comment_form})

    return render(request, 'thepost.html',
                  {'post': onePost, 'creator': creator,
                   'foe': FOE, 'fm': fm,
                   'comments': comments,
                   'new_comment': new_comment,
                   'comment_form': comment_form})

    # return HttpResponse(onePost)

# Create your views here. 5 sharing the Post in Detail Page=====================================
def sharePosts(request, id):
    post = WebModel.objects.get(id=id)
    sent = False
    if request.method == 'POST':
        # fm = ShareForm(request.POST)
        # if fm.is_valid():
        #     # Form fields passed validation
        #     # cd = fm.cleaned_data
        #     # post_url = request.build_absolute_uri(
        #     #     post.get_absolute_url())
        #     # subject = f"{cd['name']} recommends you read " f"{post.title}"
        #     # message = f"Read {post.title} at {post_url}\n\n" \
        #     #           f"{cd['name']}\'s comments: {cd['comments']}"
        #     # send_mail(subject, message, 'heisallyougot@gmail.com', [cd['to']])
        #     # sent = True
        return redirect('index')
    else:
        fm = forms.ShareForm()
        return render(request, 'thepost.html', {'fm': fm, 'creator': creator, 'foe': FOE})
