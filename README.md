# Blogging App

## Features

- Users can signup to create accounts
  - Users have username, email, bio(text), avatar(img url)
- Users can login to their accounts
- Users can write blog articles
    - Blog articles should have a heading, subheading,tags and content body
    - Assume reasonable length for heading, subheading, no limit on body
    - Body content will be html
    - Tags are an array of strings
    - Users can delete articles they have created
    - Users can update their articles
- Users can comment on blog articles
    - Comments will have a title and body
    - Titles is optional, but body is required
    - Users can delete comments they have created
- Users can like blog articles
- Users can follow other users
- Users can see list of all other users
    - Users can see profile of individual users
    - Users can search for profiles by username(or part)
- There should be a feed of all articles(reverse chronological order)
    - Ability to filter blogs by Tags.
    - Ability to filter blogs written by a specific user
    - Ability to search blog articles by title.
    - Ability to sort articles by \[date(default),likes\]
- There should be a feed of all articles of authors a user is following


### Future Scope

- Add a image upload service so that \<img>\ can be embedded in blog articles
- Add support for pagination when listing articles and users

## Technical details

### Entities

#### User

```json
{
  "id": 12,
  "username": "johndoe001",
  "email" : "john@gmail.com",
  "bio" : "I am a software engineer",
  "avatar": "https://avatars.dicebar.com/v2/johndoe001.png",
  "createdAt" : "2020-01-01T00:00:00:000Z"
}
```
> Note: `Password` has to be hashed and stored too

#### Article

```json
{
  "id": 124,
  "heading": "My first blog post",
  "slug": "my-first-blog-post",
  "subheading": "This is my first blg post",
  "tags": ["blog" , "first"],
  "content": "<p>This is my first blog post</p>",
  "authorId": 12,
  "createdAt" : "2020-01-01T00:00:00:000Z"
}
```

#### Comment

```json
{
  "id": 17,
  "title": "My first Comment",
  "body" : "This is my first comment",
  "articleId":  124,
  "authorId": 42,
  "createdAt" : "2020-01-01T00:00:00:000Z"
}
```

#### Junction Tables

#### Follows

```json
{
  "id": 133,
  "followerId": 12,
  "followeeId": 42,
  "createdAt" : "2020-01-01T00:00:00:000Z"
}
```

#### Likes

```json
{
  "id": 342,
  "LikerId": 12,
  "articleId": 42,
  "createdAt" : "2020-01-01T00:00:00:000Z"
}
```

### Schema Diagram

<img width="551" height="291" alt="image" src="https://github.com/user-attachments/assets/cd4d18df-40ba-44a4-b5b7-325e4c2b6d74" />

### API Endpoints

#### users

##### `POST/users`
create a new user(signup)

##### `POST/users/login`
login to an existing user

##### `GET/users` 📄
list all users

available query parameters:
- `?sort=date` or `sort=followers`
- `?username=something`: filter by username(i.e. username contains something)
- `?follower=johndoe`🔐 users whom `johndoe` follows
- `?following=username`🔐 users who follow `johndoe`

##### `GET/users/{userid}` 🔐
get user profile by userid

##### `GET/users/@{username}` 🔐
get user profile by username

##### `PUT/users/@{username}/follow`🔐👤
follow a user

##### `DELETE/users/@{username}/follow`🔐👤
unfollow a user

#### articles

##### `POST/articles`🔐
create a new article

##### `GET/articles/{article-slug}`
get article by slug

##### `PATCH/articles/{article-slug}`🔐👤
update article by slug

##### `DELETE/articles/{article-slug}`👤
delete article by slug

##### `PUT/articles/{article-slug}/like`
like an article by slug

##### `DELETE/articles/{article-slug}/like`👤
unlike am article by slug

##### `GET/articles` 🔐📄
get all articles

available query parameters:
- `?following=true`🔐: (default: false) get articles of users that you are following
- `?sort=date or ?sort=likes` sort by dates or likes
- `?tags=startups,tech` : filter articles by tags
- `?author=username`: filter articles by username
- `?title=something`: search articles by title (i.e. title includes `something`)

#### comments

##### `POST/articles/{article-slug}/comments`
create a new comment on a given article

##### `GET/articles/{article-slug}/comments`
get all comments on a given article

##### `DELETE/articles/{article-slug}/comments/{comment-id}`👤
delete a comment on a given article

