import { Meetup } from './meetup';
import { User } from './user';

export class MeetupComment {
  id: number;
  commentDate: string;
  commentText: string;
  title: string;
  user: User;
  replyToComment: MeetupComment;
  meetup: Meetup;
  constructor(
    id?: number,
    commentDate?: string,
    commentText?: string,
    title?: string,
    user?: User,
    replyToComment?: MeetupComment,
    meetup?: Meetup,
  ) {
    this.id = id;
    this.commentDate = commentDate;
    this.commentText = commentText;
    this.title = title;
    this.user = user;
    this.replyToComment = replyToComment;
    this.meetup = meetup;
  }
}
