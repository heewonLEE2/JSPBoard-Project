$(function(){
	
	const $card = $('#repliesCard');
	const cpath = $card.data('cpath') || '';
	const aid = $card.data('aid');
	const loginMid = String($card.data('login-mid') || '');

	const $replyList = $('#replyList');
	const $replyEmpty = $('#replyEmpty');
	const $replyCount = $('#replyCount');
	const $replyForm = $('#replyForm');
	const $rcontent = $('#rcontent');
	
	// 등록
	async function registReply(rcontent){
		
		const url = `${cpath}/reply/registReply.do`;
		const body = new URLSearchParams();
		body.append('rcontent', rcontent);
		body.append('aid', String(aid));
		body.append('mid', loginMid);
		return axios.post(url, body);
		
	}
	
	// 등록폼 서밋 이벤트 처리
	$replyForm.on('submit', async function(e){
		
		e.preventDefault();
		const text = ($rcontent.val() || '').trim();
		
		if (!text) return;
		try {
			
			await registReply(text);
			$rcontent.val('');
			await listReply();
			
		} catch(err){
			console.log(err);
			const msg = err?.response?.data?.message || '댓글 등록 중 오류 발생';
			alert(msg);
		}
		
	});
	
	// 목록
	async function listReply() {
		try {
			
			const url = `${cpath}/reply/listReply.do`;
			const {data} = await axios.get(url,{params:{aid}});
			
			if(Array.isArray(data)){
				renderReply(data);
			}else if(data && Array.isArray(data.items)){
				renderReply(data.items, Number(data.count));
			} else {
				renderReply([]);
			}
		} catch(err){
			console.error(err);
			alert('댓글 목록 오류 발생!');
		}
	}
	
	// 목록 렌더링
	function renderReply(items, countOverride) {
	      $replyList.empty();

	      const itemsArr = Array.isArray(items) ? items : [];
	      const hasItems = itemsArr.length > 0;

	      if (!hasItems) {
	        $replyEmpty.removeClass('d-none');
	        $replyCount.text('0');
	        return;
	      }

	      $replyEmpty.addClass('d-none');
	      $replyCount.text(
	        typeof countOverride === 'number' ? countOverride : itemsArr.length
	      );

	      const frags = [];
	      itemsArr.forEach(reply => {
			
	        const canDelete = loginMid && reply.mid && (loginMid === reply.mid);
	        const rid = reply.rid;

			const leftHtml = "<div>"
				+ escapeHtml(`${reply.rcontent}`)
				+ "</div><div class='text-muted small mt-1'>"
				+ escapeHtml(`${reply.mid || ''}`) + "&nbsp;"
				+ formatDateTime(`${reply.rregdate}`);
				+ "</div>";
			
	        const rightHtml = canDelete
	          ? `<div class="btn-group btn-group-sm">
	               <button type="button"
	                       class="btn btn-outline-danger btn-reply-delete"
	                       data-rid="${rid}">삭제</button>
	             </div>`
	          : `<div></div>`;

	        frags.push(`
	          <li class="list-group-item d-flex justify-content-between align-items-start" data-rid="${rid}">
	            <div>${leftHtml}</div>
	            ${rightHtml}
	          </li>
	        `);
	      });

	      $replyList.html(frags.join(''));
	    }
		
		// 삭제
		async function removeReply(rid){
			const url = `${cpath}/reply/removeReply.do`;
			const body = new URLSearchParams();
			body.append('rid', String(rid));
			return axios.post(url, body);
		}
		
		// 삭제버튼 클릭 이벤트 처리 (위임)
		$replyList.on('click', '.btn-reply-delete', async function(){
			const rid = $(this).data('rid');
			if(!rid) return;
			if(!confirm('삭제 하시겠습니까?')) return;
			try {
				await removeReply(rid);
				await listReply();
			} catch(err){
				console.error(err);
				const msg = err?.response?.data?.message || '댓글 삭제 중 오류 발생';
				alert(msg);
			}
		});
		
		
		function escapeHtml(str) {
			if (str == null) return '';
			return String(str)
				.replaceAll('&', '&amp;')
				.replaceAll('<', '&lt;')
				.replaceAll('>', '&gt;')
				.replaceAll('"', '&quot;')
				.replaceAll("'", '&#39;');
		}

		function formatDateTime(isoOrEpoch) {
			try {
				const d = (typeof isoOrEpoch === 'number') ? new Date(isoOrEpoch) : new Date(isoOrEpoch);
				if (isNaN(d.getTime())) return '';
				const p = n => String(n).padStart(2, '0');
				return `${d.getFullYear()}-${p(d.getMonth() + 1)}-${p(d.getDate())} ${p(d.getHours())}:${p(d.getMinutes())}`;
			} catch (e) { return ''; }
		}
	
		listReply();
		
});

























