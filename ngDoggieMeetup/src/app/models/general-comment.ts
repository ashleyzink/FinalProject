import { User } from './user';

export class GeneralComment {
  id: number;
  commentDate: string;
  commentText: string;
  title: string;
  user: User;
  replyToComment: GeneralComment;
  constructor(
    id?: number,
    commentDate?: string,
    commentText?: string,
    title?: string,
    user?: User,
    replyToComment?: GeneralComment,
  ) {
    this.id = id;
    this.commentDate = commentDate;
    this.commentText = commentText;
    this.title = title;
    this.user = user;
    this.replyToComment = replyToComment;
  }
}
