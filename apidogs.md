<!-- | Return Type     | Route              | Functionality     |
| --------------- | ------------------ | ----------------- |
| `List<User>`    | `GET api/users`    | Gets all users    |
| `List<DogPark>` | `GET api/dogParks` | Gets all dogParks |
| `List<Dog>`     | `GET api/dogs`     | Gets all dogs     |
| `List<Meetup>`  | `GET api/meetups`  | Gets all meetups  |

\| `User`          \|`GET api/users/{id}`                        | Gets one user by id                     |
\| `User`          \|`PUT api/admin/users/{id}`                        | Replaces an existing user by id         |
\| `void`          \|`DELETE api/admin/users/{id}`                     | Deletes an existing user by id          |
\| `List<User>`    \|`GET api/admin/users/search/{keyword}`            | To find a user by first/last/user name  |
\| `Dog`           \|`GET api/admin/users/{userId}/dogs/{id}`          | Gets one dog by id                      |
\| `Dog`           \|`PUT api/users/{userId}/dogs/{id}`                | Replaces an existing dog by id          |
\| `void`          \|`DELETE api/users/{userId}/dogs/{id}`             | Deletes an existing dog by id           |
\| `DogPark`       \|`PUT api/dogParks/{id}`                     | Replaces an existing dogPark by id  |
\| `void`          \|`DELETE api/dogParks/{id}`                  | Deletes an existing dogPark by id   | -->
------------------------------------------------------------------------------------------------------------------
\| `User`          \|`GET api/users/{id}`                        | Gets one user by id                |
\| `User`          \|`GET api/profile`                           | Replaces an existing user by id    |***
\| `User`          \|`PUT api/profile`                           | Replaces an existing user by id    |***
\| `void`          \|`DELETE api/profile`                        | Deletes an existing user by id     |***
\| `List<User>`    \|`GET api/users/search/{keyword}`            | Gets users by first/last/user name |

\| `Dog`           \|`GET api/dogs/{dogId}`                      | Gets one dog by id                 |***
\| `Dog`           \|`GET api/users/{userId}/dogs`               | Creates a new dog                  |
\| `Dog`           \|`POST api/dogs`                             | Creates a new dog                  |***
\| `Dog`           \|`PUT api/dogs/{id}`                         | Replaces an existing dog by id     |***
\| `void`          \|`DELETE api/users/{userId}/dogs/{id}`       | Deletes an existing dog by id      |***
\| `List<Dog>`     \|`GET api/dogs/search/{keyword}`             | Gets dog with matching keyword     |

\| `DogPark`       \|`GET api/dogParks/{id}`                     | Gets one dogPark by id              |(Needs ** taken off)
\| `DogPark`       \|`POST api/dogParks`                         | Creates a new dogPark               |***
\| `List<DogPark>` \|`GET api/dogParks/`                         | Gets dogParks with matching keyword |(Needs ** taken off)
\| `List<DogPark>` \|`GET api/dogParks/search/{keyword}`         | Gets dogParks with matching keyword |
\| `List<DogPark>` \|`GET api/dogParks/search/{zipcode}`         | Gets dogParks with matching keyword |(Stretch/later)

\| `Meetup`        \|`GET api/meetups/{id}`                      | Gets one meetup by id              |(Needs ** taken off)
\| `Meetup`        \|`POST api/meetups`                          | Creates a new meetup               |***
\| `Meetup`        \|`PUT api/meetups/{id}`                      | Replaces an existing meetup by id  |***
\| `void`          \|`DELETE api/meetups/{id}`                   | Deletes an existing meetup by id   |***
\| `List<Meetup>`  \|`GET api/meetups/search/{keyword}`          | Gets meetups with matching keyword |

\| `DogParkComment`\|`GET api/dogpark/{dogParkId}/dogParkComments/{comId}`| Gets one meetup by id                  |(Needs ** taken off)
\| `DogParkComment`\|`POST api/dogpark/{dogParkId}/dogParkComments/`      | Creates a new meetup                   |***
\| `DogParkComment`\|`PUT api/dogpark/{dogParkId}/dogParkComments/{comId}`  | Replaces an existing meetup by id    |***
\| `void`          \|`DELETE api/dogpark/{dogParkId}/dogParkComments/{comId}` | Deletes an existing meetup by id   |***
\| `List<DogParkComment>`  \|`GET api/dogpark/{dogParkId}/dogParkComments`  | Gets meetups with matching keyword   |(Needs ** taken off)
\| `List<DogParkComment>`  \|`GET api/dogParkComments/search/{keyword}`  | Gets meetups with matching keyword/description |(Needs ** taken off)

\| `MeetupComment`\|`GET api/meetup/{meetupId}/meetupComments/{comId}`| Gets one meetup by id                  |(Needs ** taken off)
\| `MeetupComment`\|`POST api/meetup/{meetupId}/meetupComments/`      | Creates a new meetup                   |***
\| `MeetupComment`\|`PUT api/meetup/{meetupId}/meetupComments/{comId}`  | Replaces an existing meetup by id    |***
\| `void`          \|`DELETE api/meetup/{meetupId}/meetupComments/{comId}` | Deletes an existing meetup by id   |***
\| `List<MeetupComment>`  \|`GET api/meetup/{meetupId}/meetupComments`  | Gets meetups with matching keyword   |(Needs ** taken off)
\| `List<MeetupComment>`  \|`GET api/meetupComments/search/{keyword}`  | Gets meetups with matching keyword/description |(Needs ** taken off)

\| `GeneralComment`\|`GET api/generalComments/{comId}`      | Gets one meetup by id                  |(Needs ** taken off)
\| `GeneralComment`\|`POST api/generalComments/`            | Creates a new meetup                 |***
\| `GeneralComment`\|`PUT api/generalComments/{comId}`      | Replaces an existing meetup by id    |***
\| `void`          \|`DELETE api/generalComments/{comId}`   | Deletes an existing meetup by id     |***
\| `List<GeneralComment>`  \|`GET api/general/{generalId}/generalComments`  | Gets meetups with matching keyword   |(Needs ** taken off)
\| `List<GeneralComment>`  \|`GET api/generalComments/search/{keyword}`     | Gets meetups with matching keyword/description |(Needs ** taken off)
