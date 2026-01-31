package com.revconnect.controller;

import com.revconnect.model.Comment;
import com.revconnect.model.Post;
import com.revconnect.model.User;
import com.revconnect.service.CommentService;
import com.revconnect.service.LikeService;
import com.revconnect.service.PostService;

import java.util.List;
import java.util.Scanner;

public class PostController {

    private PostService postService = new PostService();
    private LikeService likeService = new LikeService();
    private CommentService commentService = new CommentService();

    private Scanner sc = new Scanner(System.in);

    public void postMenu(User user) {

        while (true) {
            System.out.println("\n===== POSTS =====");
            System.out.println("1. Create post");
            System.out.println("2. View all posts");
            System.out.println("3. Like a post");
            System.out.println("4. Comment on a post");
            System.out.println("5. View post comments");
            System.out.println("6. Back");
            System.out.print("Choose: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    createPost(user);
                    break;
                case 2:
                    viewPosts();
                    break;
                case 3:
                    likePost(user);
                    break;
                case 4:
                    commentPost(user);
                    break;
                case 5:
                    viewComments();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice ❌");
            }
        }
    }

    // 1. Create post
    private void createPost(User user) {
        System.out.println("Write your post:");
        String content = sc.nextLine();

        Post post = new Post(user.getUserId(), content);

        if (postService.createPost(post))
            System.out.println("✅ Post created");
        else
            System.out.println("❌ Failed to create post");
    }

    // 2. View all posts
    private void viewPosts() {
        List<Post> posts = postService.getAllPosts();

        System.out.println("\n----- ALL POSTS -----");

        if (posts.isEmpty()) {
            System.out.println("No posts yet.");
            return;
        }

        for (Post p : posts) {
            int likes = likeService.countLikes(p.getPostId());

            System.out.println("Post ID : " + p.getPostId());
            System.out.println("User ID : " + p.getUserId());
            System.out.println("Content : " + p.getContent());
            System.out.println("❤️ Likes : " + likes);
            System.out.println("🕒 " + p.getCreatedAt());
            System.out.println("------------------------");
        }
    }

    // 3. Like post
    private void likePost(User user) {
        System.out.print("Enter Post ID to like: ");
        int postId = sc.nextInt();
        sc.nextLine();

        if (likeService.likePost(postId, user.getUserId()))
            System.out.println("❤️ Liked successfully");
        else
            System.out.println("❌ Like failed");
    }

    // 4. Comment on post
    private void commentPost(User user) {
        System.out.print("Enter Post ID to comment: ");
        int postId = sc.nextInt();
        sc.nextLine();

        System.out.print("Write comment: ");
        String text = sc.nextLine();

        Comment c = new Comment(postId, user.getUserId(), text);

        if (commentService.addComment(c))
            System.out.println("✅ Comment added");
        else
            System.out.println("❌ Failed to add comment");
    }

    // 5. View comments
    private void viewComments() {
        System.out.print("Enter Post ID: ");
        int postId = sc.nextInt();
        sc.nextLine();

        List<Comment> comments = commentService.getComments(postId);

        System.out.println("\n---- COMMENTS ----");

        if (comments.isEmpty()) {
            System.out.println("No comments yet.");
            return;
        }

        for (Comment c : comments) {
            System.out.println("User ID : " + c.getUserId());
            System.out.println("Comment : " + c.getComment());
            System.out.println("🕒 " + c.getCreatedAt());
            System.out.println("--------------------");
        }
    }
}

